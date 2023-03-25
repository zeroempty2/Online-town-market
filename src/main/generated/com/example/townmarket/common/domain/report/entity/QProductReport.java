package com.example.townmarket.common.domain.report.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductReport is a Querydsl query type for ProductReport
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductReport extends EntityPathBase<ProductReport> {

    private static final long serialVersionUID = -21535748L;

    public static final QProductReport productReport = new QProductReport("productReport");

    public final com.example.townmarket.common.QTimeStamped _super = new com.example.townmarket.common.QTimeStamped(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath reason = createString("reason");

    public final EnumPath<ProductReport.ReportEnum> reportEnum = createEnum("reportEnum", ProductReport.ReportEnum.class);

    public final NumberPath<Long> reportUserId = createNumber("reportUserId", Long.class);

    public QProductReport(String variable) {
        super(ProductReport.class, forVariable(variable));
    }

    public QProductReport(Path<? extends ProductReport> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductReport(PathMetadata metadata) {
        super(ProductReport.class, metadata);
    }

}

