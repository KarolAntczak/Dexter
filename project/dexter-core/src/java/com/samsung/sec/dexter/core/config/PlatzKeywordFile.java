package com.samsung.sec.dexter.core.config;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.samsung.sec.dexter.core.exception.DexterRuntimeException;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class PlatzKeywordFile {
    private String keyword;

    public void loadFromFile(final File file) {
        Map<String, Object> keywordMap = getKeywordMap(file);
        setFields(keywordMap);

    }

    protected Map<String, Object> getKeywordMap(final File keywordFile) {
        if (keywordFile.exists() == false) {
            throw new DexterRuntimeException("There is no " + DexterConfig.PLATZ_KEYWORD_FILENAME
                    + " file : " + keywordFile.getName());
        }
        final StringBuilder keywordJson = new StringBuilder(50);
        try {
            for (String content : Files.readLines(keywordFile, Charsets.UTF_8)) {
                keywordJson.append(content);
                if (content.indexOf("}") >= 0) {
                    break;
                }
            }

            keywordJson.trimToSize();

            final Gson gson = new Gson();
            @SuppressWarnings("unchecked")
            Map<String, Object> keywordMap = gson.fromJson(keywordJson.toString(), Map.class);
            return keywordMap;

        } catch (IOException e) {
            throw new DexterRuntimeException(e.getMessage(), e);
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new DexterRuntimeException("PLATZ keyword Json file is not valid. " + e.getMessage(), e);
        }
    }

    public void setFields(final Map<String, Object> params) {
        try {
            setKeyword((String) params.get("keyword"));
        } catch (NullPointerException e) {
            throw new DexterRuntimeException(e.getMessage(), e);
        }
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
