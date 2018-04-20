//package com.ciyun.mvvmdemo.cache;
//
//import android.content.Context;
//import android.os.Environment;
//import android.os.StatFs;
//import android.util.Base64;
//
//import com.ciyun.mvvmdemo.utils.SharedPreferencesUtils;
//
//import java.io.BufferedReader;
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.ObjectInputStream;
//import java.io.OutputStream;
//import java.util.prefs.Preferences;
//
//public class DataFileCache {
//    public static String FUJIN_CACHE_NAME = "A"; // 附近的人缓存目录
//    public static String BENQUN_CACHE_NAME = "B"; // 社群缓存目录
//    public static String BENYOU_CACHE_NAME = "C"; // 好友数据缓存目录
//    public static String USER_CACHE_NAME = "D"; // 个人页面缓存
//
//    public static final String CACHDIR = File.separator + "benben"
//            + File.separator + "cache" + File.separator;// 缓存根目录
//
//    private static final String Suffix = ".benben";// 缓存文件后缀名
//    private static final int FREE_SD_SPACE_NEEDED_TO_CACHE = 1;// 缓存空间大于1M才能进行缓存
//    private int MB = 1024 * 1024;
//    private String folder = "";
//
//    public static final String Favorit_CACHE_NAME = "Favorit";
//    public static final String LOG_CACHE_NAME = "Log"; // 日志文件目录
//    public static final int Favorit_Key = 0x100;
//    public static final String Category_CACHE_NAME = "Category";
//    public static final int Category_Key = 0x101;//
//
//    private Context mContext;
//
//    /**
//     * @param folder
//     *            缓存目录文件夹
//     * */
//    public DataFileCache(Context context, String folder) {
//        this.folder = folder;
//        this.mContext = context;
//    }
//
//    /**
//     * 缓存数据
//     *
//     * @param name
//     *            缓存文件名
//     * @param o
//     *            需要缓存的对象
//     * */
//    public boolean saveFile(String name, Object o) {
//        // 判断sdcard上的空间
//        if (FREE_SD_SPACE_NEEDED_TO_CACHE > freeSpaceOnSd()) {
//            // SD空间不足
//            return false;
//        }
//        String value = BaseHelper.object2String(o);
//        String filename = convertUrlToFileName(name);
//        String dir = getDirectory();
//        File file = new File(dir + File.separator + filename);
//        OutputStream outStream = null;
//        try {
//            if (file.exists()) {
//                file.delete();
//            }
//            file.createNewFile();
//            outStream = new FileOutputStream(file);
//            outStream.write(value.getBytes());
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            if (outStream != null) {
//                try {
//                    outStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    /**
//     * 保存日志文件
//     *
//     * @param name
//     * @param obj
//     */
//    public void saveLogFile(String name, String obj) {
//        // 判断sdcard上的空间
//        if (FREE_SD_SPACE_NEEDED_TO_CACHE > freeSpaceOnSd()) {
//            // SD空间不足
//            return;
//        }
//        String filename = convertUrlToFileName(name);
//        String dir = getDirectory();
//        FileWriter fileWriter;
//        try {
//            fileWriter = new FileWriter(dir + File.separator + filename, true);
//            fileWriter.write(obj);
//            fileWriter.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 判断文件日志文件
//     *
//     * @param name
//     * @return
//     */
//    public boolean isExist(String name) {
//        final String path = getDirectory() + File.separator
//                + convertUrlToFileName(name);
//        File file = new File(path);
//        return file.exists();
//    }
//
//    /**
//     * 读取缓存文件，并返回数据
//     *
//     * @param name
//     *            缓存文件名
//     * */
//    public Object openFile(String name) {
//        Object o = null;
//        final String path = getDirectory() + File.separator
//                + convertUrlToFileName(name);
//        File file = new File(path);
//        if (file.exists()) {
//            try {
//                FileInputStream inputStream = new FileInputStream(file);
//                BufferedReader br = new BufferedReader(new InputStreamReader(
//                        inputStream));
//                StringBuilder sb = new StringBuilder("");
//                String line = null;
//                while ((line = br.readLine()) != null) {
//                    sb.append(line);
//                }
//                String value = sb.toString();
//                if (!"".equals(value)) {
//                    byte[] bytes = Base64.decode(value);
//                    ByteArrayInputStream navBais = new ByteArrayInputStream(
//                            bytes);
//                    ObjectInputStream navOis = new ObjectInputStream(navBais);
//                    o = (Object) navOis.readObject();
//                    navBais.close();
//                    navOis.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//        return o;
//    }
//
//    /**
//     * 清空缓存目录下的文件
//     * */
//    public boolean clear() {
//        File dir = new File(getDirectory());
//        File[] files = dir.listFiles();
//        if (files == null) {
//            return true;
//        }
//        if (!Environment.getExternalStorageState().equals(
//                Environment.MEDIA_MOUNTED)) {
//            return false;
//        }
//        for (int i = 0; i < files.length; i++) {
//            files[i].delete();
//        }
//        dir.delete();
//        return true;
//    }
//
//    /**
//     * 清空商品分类缓存文件
//     * */
//    public boolean clearCategory() {
//        final String path = getDirectory();
//        File dir = new File(path);
//        try {
//            File[] files = dir.listFiles();
//            if (!Environment.getExternalStorageState().equals(
//                    Environment.MEDIA_MOUNTED)) {
//                return false;
//            }
//            for (int i = 0; i < files.length; i++) {
//                files[i].delete();
//            }
//            dir.delete();
//        } catch (Exception e) {
//            // TODO: handle exception
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 计算sdcard上的剩余空间
//     *
//     * @return
//     */
//    private int freeSpaceOnSd() {
//        StatFs stat = new StatFs(Environment.getExternalStorageDirectory()
//                .getPath());
//        double sdFreeMB = ((double) stat.getAvailableBlocks() * (double) stat
//                .getBlockSize()) / MB;
//        return (int) sdFreeMB;
//    }
//
//    /**
//     * 转成统一的文件名
//     * */
//    private String convertUrlToFileName(String fileName) {
//        return BaseHelper.delSignBlank(fileName) + Suffix;
//    }
//
//    /**
//     * 获得缓存目录 根据用户id获取当前用户的缓存目录 不同用户缓存目录是不同的
//     * */
//    private String getDirectory() {
//        String dir = "";
//
//        LoginBean loginBean = SharedPreferencesUtils.getObject(mContext,
//                Preferences.LOGIN_RESPONSE, LoginBean.class);// 获取当前登录用户id
//        if (loginBean != null && loginBean.getPersonId() != null) {
//            // 缓存路径，SD卡+缓存文件名+当前用户+缓存内容的文件名
//            dir = getSDPath() + CACHDIR + loginBean.getPersonId()
//                    + File.separator + folder;
//        } else {
//            dir = getSDPath() + CACHDIR + "default" + File.separator + folder;
//        }
//
//        try {
//            File file = new File(dir);
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dir;
//    }
//
//    /**
//     * 取SD卡路径不带/
//     * */
//    private String getSDPath() {
//        File sdDir = null;
//        boolean sdCardExist = Environment.getExternalStorageState().equals(
//                android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
//        if (sdCardExist) {
//            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
//        }
//        if (sdDir != null) {
//            return sdDir.toString();
//        } else {
//            return "";
//        }
//    }
//
//    public boolean saveTxt(String name, String value) {
//        // 判断sdcard上的空间
//        if (FREE_SD_SPACE_NEEDED_TO_CACHE > freeSpaceOnSd()) {
//            // SD空间不足
//            return false;
//        }
//        String filename = name + ".txt";
//        String dir = getDirectory();
//        File file = new File(dir + File.separator + filename);
//        OutputStream outStream = null;
//        try {
//            if (file.exists()) {
//                file.delete();
//            }
//            file.createNewFile();
//            outStream = new FileOutputStream(file);
//            outStream.write(value.getBytes());
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (outStream != null) {
//                try {
//                    outStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return false;
//    }
//
//    /**
//     * 读取缓存文件，并返回数据
//     *
//     * @param name
//     *            读取缓存文件的文件名
//     * */
//    public String openTxt(String name) {
//        final String path = getDirectory() + File.separator + name + ".txt";
//        File file = new File(path);
//        if (file.exists()) {
//            try {
//                FileInputStream inputStream = new FileInputStream(file);
//                BufferedReader br = new BufferedReader(new InputStreamReader(
//                        inputStream));
//                StringBuilder sb = new StringBuilder("");
//                String line = null;
//                while ((line = br.readLine()) != null) {
//                    sb.append(line);
//                }
//                String value = sb.toString();
//                return value;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return "";
//    }
//}