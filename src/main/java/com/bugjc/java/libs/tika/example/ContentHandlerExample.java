/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bugjc.java.libs.tika.example;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ContentHandlerDecorator;
import org.apache.tika.sax.ToXMLContentHandler;
import org.apache.tika.sax.XHTMLContentHandler;
import org.apache.tika.sax.xpath.Matcher;
import org.apache.tika.sax.xpath.MatchingContentHandler;
import org.apache.tika.sax.xpath.XPathParser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Examples of using different Content Handlers to
 * get different parts of the file's contents
 */
public class ContentHandlerExample {

    public static void main(String[] args) throws TikaException, SAXException, IOException {
        //System.out.println(new ContentHandlerExample().parseToPlainText());
        new ContentHandlerExample().getMetadata();
    }

    public String getMetadata() throws IOException, TikaException, SAXException {
        try (InputStream stream = ContentHandlerExample.class.getResourceAsStream("/test/TEST1.xlsx")) {
            Parser parser = new AutoDetectParser();
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            ParseContext context = new ParseContext();
            parser.parse(stream, handler, metadata, context);
            handler.startDocument();
            System.out.println(handler.toString());

            //getting the list of all meta data elements
            String[] metadataNames = metadata.names();

            for(String name : metadataNames) {
                System.out.println(name + ": " + metadata.get(name));
            }
        }
        return null;
    }

    /**
     * Example of extracting the plain text of the contents.
     * Will return only the "body" part of the document
     */
    public String parseToPlainText() throws IOException, SAXException, TikaException {
        BodyContentHandler handler = new BodyContentHandler();

        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        try (InputStream stream = ContentHandlerExample.class.getResourceAsStream("/test/TEST1.xlsx")) {
            parser.parse(stream, handler, metadata);
            return handler.toString();
        }
    }

    /**
     * Example of extracting the contents as HTML, as a string.
     */
    public String parseToHTML() throws IOException, SAXException, TikaException {
        ContentHandler handler = new ToXMLContentHandler();

        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        try (InputStream stream = ContentHandlerExample.class.getResourceAsStream("test.doc")) {
            parser.parse(stream, handler, metadata);
            return handler.toString();
        }
    }

    /**
     * Example of extracting just the body as HTML, without the
     * head part, as a string
     */
    public String parseBodyToHTML() throws IOException, SAXException, TikaException {
        ContentHandler handler = new BodyContentHandler(
                new ToXMLContentHandler());

        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        try (InputStream stream = ContentHandlerExample.class.getResourceAsStream("test.doc")) {
            parser.parse(stream, handler, metadata);
            return handler.toString();
        }
    }

    /**
     * Example of extracting just one part of the document's body,
     * as HTML as a string, excluding the rest
     */
    public String parseOnePartToHTML() throws IOException, SAXException, TikaException {
        // Only get things under html -> body -> div (class=header)
        XPathParser xhtmlParser = new XPathParser("xhtml", XHTMLContentHandler.XHTML);
        Matcher divContentMatcher = xhtmlParser.parse("/xhtml:html/xhtml:body/xhtml:div/descendant::node()");
        ContentHandler handler = new MatchingContentHandler(
                new ToXMLContentHandler(), divContentMatcher);

        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        try (InputStream stream = ContentHandlerExample.class.getResourceAsStream("test2.doc")) {
            parser.parse(stream, handler, metadata);
            return handler.toString();
        }
    }

    protected final int MAXIMUM_TEXT_CHUNK_SIZE = 40;

    /**
     * Example of extracting the plain text in chunks, with each chunk
     * of no more than a certain maximum size
     */
    public List<String> parseToPlainTextChunks() throws IOException, SAXException, TikaException {
        final List<String> chunks = new ArrayList<>();
        chunks.add("");
        ContentHandlerDecorator handler = new ContentHandlerDecorator() {
            @Override
            public void characters(char[] ch, int start, int length) {
                String lastChunk = chunks.get(chunks.size() - 1);
                String thisStr = new String(ch, start, length);

                if (lastChunk.length() + length > MAXIMUM_TEXT_CHUNK_SIZE) {
                    chunks.add(thisStr);
                } else {
                    chunks.set(chunks.size() - 1, lastChunk + thisStr);
                }
            }
        };

        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        try (InputStream stream = ContentHandlerExample.class.getResourceAsStream("test2.doc")) {
            parser.parse(stream, handler, metadata);
            return chunks;
        }
    }
}
