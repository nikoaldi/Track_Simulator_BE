package org.acme;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class OwnPlatformDeserializer  extends ObjectMapperDeserializer<ownPlatformData> {
    public OwnPlatformDeserializer(){
        super(ownPlatformData.class);
    }

}
