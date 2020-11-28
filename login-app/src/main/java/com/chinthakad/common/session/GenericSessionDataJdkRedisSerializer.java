package com.chinthakad.common.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GenericSessionDataJdkRedisSerializer implements RedisSerializer<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericSessionDataJdkRedisSerializer.class);
    private RedisSerializer<Object> serializer;
    private Set<Class> sessionContextClassesSet = new HashSet<>();

    public GenericSessionDataJdkRedisSerializer(Class... sessionContextClasses) {

        this.serializer = new JdkSerializationRedisSerializer();
        sessionContextClassesSet.addAll(Arrays.asList(sessionContextClasses));
        sessionContextClassesSet.add(CommonSharedContext.class);
        LOGGER.info("=== Redis Serialization would support session-scope beans from: {}",
                sessionContextClassesSet);
    }


    @Override
    public byte[] serialize(Object o) throws SerializationException {
        return this.serializer.serialize(o);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("deserialization attempt made");
            }
            return this.serializer.deserialize(bytes);
        } catch (SerializationException serEx) {
            Throwable rootCause = serEx.getRootCause();
            if (rootCause instanceof ClassNotFoundException) {
                ClassNotFoundException cnfE = (ClassNotFoundException) rootCause;
                String missingClass = cnfE.getMessage();
                if (sessionContextClassesSet
                        .stream()
                        .map(Class::getName)
                        .anyMatch(missingClass::equals)) {
                    LOGGER.error("Unable to Deserialize and incompatible class {} comes from an expected classes of {}",
                            missingClass, sessionContextClassesSet);
                    throw serEx;
                }
                LOGGER.warn("Deserialization Failed, However {} is not expected by the application, therefore its safe", missingClass);
                return null;
            }
            LOGGER.error("Unable to Serialize", serEx);
            return serEx;
        }
    }
}
