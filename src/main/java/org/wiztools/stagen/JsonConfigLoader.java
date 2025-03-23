package org.wiztools.stagen;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author subwiz
 */
public class JsonConfigLoader implements ConfigLoader {

    @Override
    public Map<String, Object> getConfigMap(File dataFile) throws ExecutorException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> out = mapper.readValue(dataFile,
                    new TypeReference<Map<String, Object>>() {});
            return out;
        }
        catch(IOException ex) {
            throw new ExecutorException(ex);
        }
    }

    @Override
    public String getFileExtension() {
        return ".json";
    }

}
