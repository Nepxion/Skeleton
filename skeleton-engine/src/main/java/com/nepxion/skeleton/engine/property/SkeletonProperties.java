package com.nepxion.skeleton.engine.property;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

import com.nepxion.banner.BannerConstant;
import com.nepxion.banner.Description;
import com.nepxion.banner.LogoBanner;
import com.nepxion.banner.NepxionBanner;
import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.util.MathsUtil;
import com.taobao.text.Color;

public class SkeletonProperties implements Serializable {
    static {
        /*String bannerShown = System.getProperty(BannerConstant.BANNER_SHOWN, "true");
        if (Boolean.valueOf(bannerShown)) {
            System.out.println("");
            System.out.println("╔═══╦╗    ╔╗    ╔╗");
            System.out.println("║╔═╗║║    ║║   ╔╝╚╗");
            System.out.println("║╚══╣║╔╦══╣║╔══╬╗╔╬══╦══╗");
            System.out.println("╚══╗║╚╝╣║═╣║║║═╣║║║╔╗║╔╗║");
            System.out.println("║╚═╝║╔╗╣║═╣╚╣║═╣║╚╣╚╝║║║║");
            System.out.println("╚═══╩╝╚╩══╩═╩══╝╚═╩══╩╝╚╝");
            System.out.println("Nepxion Skeleton  v" + SkeletonConstant.SKELETON_VERSION);
            System.out.println("");
        }*/

        LogoBanner logoBanner = new LogoBanner(SkeletonProperties.class, "/com/nepxion/skeleton/resource/logo.txt", "Welcome to Nepxion", 8, 5, new Color[] { Color.red, Color.green, Color.cyan, Color.blue, Color.yellow, Color.magenta, Color.red, Color.green }, true);

        NepxionBanner.show(logoBanner, new Description(BannerConstant.VERSION + ":", SkeletonConstant.SKELETON_VERSION, 0, 1), new Description(BannerConstant.GITHUB + ":", BannerConstant.NEPXION_GITHUB + "/Skeleton", 0, 1));
    }

    private static final long serialVersionUID = 1722927234615067236L;

    private final Map<String, String> map = new LinkedHashMap<String, String>();

    private String content;

    // 配置文件含中文，stringEncoding必须为GBK，readerEncoding必须为UTF-8，文本文件编码必须为ANSI
    public SkeletonProperties(String path, String stringEncoding, String readerEncoding) throws IOException {
        this(new SkeletonContent(path, stringEncoding).getContent(), readerEncoding);
    }

    // 配置文件含中文，stringEncoding必须为UTF-8，readerEncoding必须为UTF-8
    public SkeletonProperties(byte[] bytes, String stringEncoding, String readerEncoding) throws IOException {
        this(new String(bytes, stringEncoding), readerEncoding);
    }

    // 配置文件含中文，encoding必须为UTF-8
    public SkeletonProperties(String content, String encoding) throws IOException {
        this.content = content;

        InputStream inputStream = null;
        Reader reader = null;
        try {
            inputStream = IOUtils.toInputStream(content, encoding);
            reader = new InputStreamReader(inputStream, encoding);

            Properties properties = new Properties();
            properties.load(reader);
            for (Iterator<Object> iterator = properties.keySet().iterator(); iterator.hasNext();) {
                String key = iterator.next().toString();
                String value = properties.getProperty(key);
                put(key, value);
            }
        } finally {
            if (reader != null) {
                IOUtils.closeQuietly(reader);
            }

            if (inputStream != null) {
                IOUtils.closeQuietly(inputStream);
            }
        }
    }

    public String getContent() {
        return content;
    }

    public void put(String key, String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value is null for key [" + key + "]");
        }

        Long result = MathsUtil.calculate(value);
        if (result != null) {
            map.put(key, String.valueOf(result));
        } else {
            map.put(key, value);
        }
    }

    public Map<String, String> getMap() {
        return map;
    }

    public String getString(String key) {
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException("Key [" + key + "] isn't found in properties");
        }

        return map.get(key);
    }

    public String getString(String key, String defaultValue) {
        String value = getString(key);
        if (value != null) {
            return value;
        } else {
            return defaultValue;
        }
    }

    public boolean getBoolean(String key) {
        String value = getString(key);
        if (value != null) {
            try {
                return Boolean.parseBoolean(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] can't be parsed to boolean", e);
            }
        } else {
            throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] is null");
        }
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        String value = getString(key);
        if (value != null) {
            try {
                return Boolean.parseBoolean(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] can't be parsed to boolean", e);
            }
        } else {
            return defaultValue;
        }
    }

    public int getInteger(String key) {
        String value = getString(key);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] can't be parsed to int", e);
            }
        } else {
            throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] is null");
        }
    }

    public int getInteger(String key, int defaultValue) {
        String value = getString(key);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] can't be parsed to int", e);
            }
        } else {
            return defaultValue;
        }
    }

    public long getLong(String key) {
        String value = getString(key);
        if (value != null) {
            try {
                return Long.parseLong(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] can't be parsed to long", e);
            }
        } else {
            throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] is null");
        }
    }

    public long getLong(String key, long defaultValue) {
        String value = getString(key);
        if (value != null) {
            try {
                return Long.parseLong(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] can't be parsed to long", e);
            }
        } else {
            return defaultValue;
        }
    }

    public short getShort(String key, short defaultValue) {
        String value = getString(key);
        if (value != null) {
            try {
                return Short.parseShort(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] can't be parsed to short", e);
            }
        } else {
            throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] is null");
        }
    }

    public Short getShort(String key, Short defaultValue) {
        String value = getString(key);
        if (value != null) {
            try {
                return Short.parseShort(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] can't be parsed to short", e);
            }
        } else {
            return defaultValue;
        }
    }

    public byte getByte(String key) {
        String value = getString(key);
        if (value != null) {
            try {
                return Byte.parseByte(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] can't be parsed to byte", e);
            }
        } else {
            throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] is null");
        }
    }

    public byte getByte(String key, byte defaultValue) {
        String value = getString(key);
        if (value != null) {
            try {
                return Byte.parseByte(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] can't be parsed to byte", e);
            }
        } else {
            return defaultValue;
        }
    }

    public double getDouble(String key) {
        String value = getString(key);
        if (value != null) {
            try {
                return Double.parseDouble(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] can't be parsed to double", e);
            }
        } else {
            throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] is null");
        }
    }

    public double getDouble(String key, double defaultValue) {
        String value = getString(key);
        if (value != null) {
            try {
                return Double.parseDouble(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] can't be parsed to double", e);
            }
        } else {
            return defaultValue;
        }
    }

    public float getFloat(String key) {
        String value = getString(key);
        if (value != null) {
            try {
                return Float.parseFloat(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] can't be parsed to float", e);
            }
        } else {
            throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] is null");
        }
    }

    public float getFloat(String key, float defaultValue) {
        String value = getString(key);
        if (value != null) {
            try {
                return Float.parseFloat(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] can't be parsed to float", e);
            }
        } else {
            return defaultValue;
        }
    }

    public BigInteger getBigInteger(String key) {
        String value = getString(key);
        if (value != null) {
            try {
                return BigInteger.valueOf(Long.parseLong(value));
            } catch (Exception e) {
                throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] can't be parsed to BigInteger", e);
            }
        } else {
            throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] is null");
        }
    }

    public BigInteger getBigInteger(String key, BigInteger defaultValue) {
        String value = getString(key);
        if (value != null) {
            try {
                return BigInteger.valueOf(Long.parseLong(value));
            } catch (Exception e) {
                throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] can't be parsed to BigInteger", e);
            }
        } else {
            return defaultValue;
        }
    }

    public BigDecimal getBigDecimal(String key) {
        String value = getString(key);
        if (value != null) {
            try {
                return BigDecimal.valueOf(Double.parseDouble(value));
            } catch (Exception e) {
                throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] can't be parsed to BigDecimal", e);
            }
        } else {
            throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] is null");
        }
    }

    public BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
        String value = getString(key);
        if (value != null) {
            try {
                return BigDecimal.valueOf(Double.parseDouble(value));
            } catch (Exception e) {
                throw new IllegalArgumentException("Value [" + value + "] for key [" + key + "] can't be parsed to BigDecimal", e);
            }
        } else {
            return defaultValue;
        }
    }

    public void mergeProperties(SkeletonProperties properties) {
        map.putAll(properties.getMap());
    }

    @Override
    public String toString() {
        return map.toString();
    }
}