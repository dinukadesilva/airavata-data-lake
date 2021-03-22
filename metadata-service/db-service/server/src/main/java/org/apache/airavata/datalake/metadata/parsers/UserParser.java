package org.apache.airavata.datalake.metadata.parsers;

import com.google.protobuf.GeneratedMessageV3;
import org.apache.airavata.datalake.metadata.backend.neo4j.model.nodes.Entity;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserParser implements Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserParser.class);

    @Autowired
    private DozerBeanMapper dozerBeanMapper;


    @Override
    public Entity parse(GeneratedMessageV3 entity, Entity parentEntity, ExecutionContext executionContext) {
        if (entity instanceof org.apache.airavata.datalake.metadata.service.User) {
            org.apache.airavata.datalake.metadata.service.User user =
                    (org.apache.airavata.datalake.metadata.service.User) entity;
            return dozerBeanMapper.map(user,
                    org.apache.airavata.datalake.metadata.backend.neo4j.model.nodes.User.class);
        } else {
            String msg = "Wrong entity type detected for parser User Parser, Expected User";
            LOGGER.error(msg);
            throw new RuntimeException(msg);
        }
    }

    @Override
    public Entity parse(GeneratedMessageV3 entity, ExecutionContext executionContext) {
        return this.parse(entity, null, executionContext);
    }

    @Override
    public Entity parse(GeneratedMessageV3 entity) {
        return this.parse(entity, null, new ExecutionContext());
    }
}
