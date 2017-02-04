package com.robotnec.budget.app.persistence.schema;

import com.yahoo.squidb.annotations.ColumnSpec;
import com.yahoo.squidb.annotations.PrimaryKey;
import com.yahoo.squidb.annotations.TableModelSpec;

@TableModelSpec(className = "CategoryRecord", tableName = "category")
public class CategorySchema {

    @PrimaryKey
    private long id;

    @ColumnSpec(constraints = "NOT NULL UNIQUE")
    private String name;
}