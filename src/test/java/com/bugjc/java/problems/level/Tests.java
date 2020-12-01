package com.bugjc.java.problems.level;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tests<I, O> {
    private I input;
    private O output;
}
