package com.assignment.domain;

import lombok.NonNull;
import lombok.Value;

/**
 * 芸人 Model
 */
@Value
@NonNull
public class Comedian {
    // ID
    int id;
    // 名前
    String name;
}
