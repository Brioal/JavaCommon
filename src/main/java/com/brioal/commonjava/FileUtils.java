



import com.brioal.commonjava.RandomUtils;
import com.brioal.commonjava.TextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 2018/3/13.
 */

public class FileUtils {
    // 记录器
    public static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private static final String FOLDER_SEPARATOR = "/";
    private static final char EXTENSION_SEPARATOR = '.';

    /**
     * @desc:判断指定路径是否存在，如果不存在，根据参数决定是否新建
     * @autor:chenssy
     * @date:2014年8月7日
     *
     * @param filePath
     * 			指定的文件路径
     * @param isNew
     * 			true：新建、false：不新建
     * @return 存在返回TRUE，不存在返回FALSE
     */
    public static boolean isExist(String filePath,boolean isNew){
        File file = new File(filePath);
        if(!file.exists() && isNew){
            return file.mkdirs();    //新建文件路径
        }
        return false;
    }

    /**
     * 获取文件名，构建结构为 prefix + yyyyMMddHH24mmss + 10位随机数 + suffix + .type
     * @autor:chenssy
     * @date:2014年8月11日
     *
     * @param type
     * 				文件类型
     * @param prefix
     * 				前缀
     * @param suffix
     * 				后缀
     * @return
     */
    public static String getFileName(String type,String prefix,String suffix){
        String date = DateUtils.getCurrentTime("yyyyMMddHH24mmss");   //当前时间
        String random = RandomUtils.generateNumberString(10);   //10位随机数

        //返回文件名
        return prefix + date + random + suffix + "." + type;
    }

    /**
     * 获取文件名，文件名构成:当前时间 + 10位随机数 + .type
     * @autor:chenssy
     * @date:2014年8月11日
     *
     * @param type
     * 				文件类型
     * @return
     */
    public static String getFileName(String type){
        return getFileName(type, "", "");
    }

    /**
     * 获取文件名，文件构成：当前时间 + 10位随机数
     * @autor:chenssy
     * @date:2014年8月11日
     *
     * @return
     */
    public static String getFileName(){
        String date = DateUtils.getCurrentTime("yyyyMMddHH24mmss");   //当前时间
        String random = RandomUtils.generateNumberString(10);   //10位随机数

        //返回文件名
        return date + random;
    }

    /**
     * 获取指定文件的大小
     *
     * @param file
     * @return
     * @throws Exception
     *
     * @author:chenssy
     * @date : 2016年4月30日 下午9:10:12
     */
    @SuppressWarnings("resource")
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
        }
        return size;
    }

    /**
     * 删除所有文件，包括文件夹
     *
     * @author : chenssy
     * @date : 2016年5月23日 下午12:41:08
     *
     * @param dirpath
     */
    public void deleteAll(String dirpath) {
        File path = new File(dirpath);
        try {
            if (!path.exists())
                return;// 目录不存在退出
            if (path.isFile()) // 如果是文件删除
            {
                path.delete();
                return;
            }
            File[] files = path.listFiles();// 如果目录中有文件递归删除文件
            for (int i = 0; i < files.length; i++) {
                deleteAll(files[i].getAbsolutePath());
            }
            path.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制文件或者文件夹
     *
     * @author : chenssy
     * @date : 2016年5月23日 下午12:41:59
     *
     * @param inputFile
     * 						源文件
     * @param outputFile
     * 						目的文件
     * @param isOverWrite
     * 						是否覆盖文件
     * @throws java.io.IOException
     */
    public static void copy(File inputFile, File outputFile, boolean isOverWrite)
            throws IOException {
        if (!inputFile.exists()) {
            throw new RuntimeException(inputFile.getPath() + "源目录不存在!");
        }
        copyPri(inputFile, outputFile, isOverWrite);
    }

    /**
     * 复制文件或者文件夹
     *
     * @author : chenssy
     * @date : 2016年5月23日 下午12:43:24
     *
     * @param inputFile
     * 						源文件
     * @param outputFile
     * 						目的文件
     * @param isOverWrite
     * 						是否覆盖文件
     * @throws java.io.IOException
     */
    private static void copyPri(File inputFile, File outputFile, boolean isOverWrite) throws IOException {
        if (inputFile.isFile()) {		//文件
            copySimpleFile(inputFile, outputFile, isOverWrite);
        } else {
            if (!outputFile.exists()) {		//文件夹
                outputFile.mkdirs();
            }
            // 循环子文件夹
            for (File child : inputFile.listFiles()) {
                copy(child, new File(outputFile.getPath() + "/" + child.getName()), isOverWrite);
            }
        }
    }

    /**
     * 复制单个文件
     *
     * @author : chenssy
     * @date : 2016年5月23日 下午12:44:07
     *
     * @param inputFile
     * 						源文件
     * @param outputFile
     * 						目的文件
     * @param isOverWrite
     * 						是否覆盖
     * @throws java.io.IOException
     */
    private static void copySimpleFile(File inputFile, File outputFile,
                                       boolean isOverWrite) throws IOException {
        if (outputFile.exists()) {
            if (isOverWrite) {		//可以覆盖
                if (!outputFile.delete()) {
                    throw new RuntimeException(outputFile.getPath() + "无法覆盖！");
                }
            } else {
                // 不允许覆盖
                return;
            }
        }
        InputStream in = new FileInputStream(inputFile);
        OutputStream out = new FileOutputStream(outputFile);
        byte[] buffer = new byte[1024];
        int read = 0;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        in.close();
        out.close();
    }

    /**
     * 获取文件的MD5
     *
     * @author : chenssy
     * @date : 2016年5月23日 下午12:50:38
     *
     * @param file
     * 				文件
     * @return
     */
    public static String getFileMD5(File file){
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    /**
     * 获取文件的后缀
     *
     * @author : chenssy
     * @date : 2016年5月23日 下午12:51:59
     *
     * @param file
     * 				文件
     * @return
     */
    public static String getFileSuffix(String file) {
        if (file == null) {
            return null;
        }
        int extIndex = file.lastIndexOf(EXTENSION_SEPARATOR);
        if (extIndex == -1) {
            return null;
        }
        int folderIndex = file.lastIndexOf(FOLDER_SEPARATOR);
        if (folderIndex > extIndex) {
            return null;
        }
        return file.substring(extIndex + 1);
    }

    /**
     * 文件重命名
     *
     * @author : chenssy
     * @date : 2016年5月23日 下午12:56:05
     *
     * @param oldPath
     * 					老文件
     * @param newPath
     * 					新文件
     */
    public boolean renameDir(String oldPath, String newPath) {
        File oldFile = new File(oldPath);// 文件或目录
        File newFile = new File(newPath);// 文件或目录

        return oldFile.renameTo(newFile);// 重命名
    }








    /**
     * 将上传的文件保存普通文件并且返回文件实体
     *
     * @param file
     * @param
     */
    public static File saveRegularFileAndReturnPath(MultipartFile file) {
        if (file == null) {
            return null;
        }
        logger.info("将上传的文件保存普通文件并且返回文件实体:");
        // 文件类型
        String dot = getFileDot(file);
        logger.info("文件类型:" + dot);
        // 获取要存储的目标文件
        File targetFile = getRegularFormatFile(dot);
        logger.info("目标文件路径:" + targetFile.getAbsolutePath());
        // 保存文件
        try {
            FileCopyUtils.copy(file.getBytes(), targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回文件
        return targetFile;
    }


    /**
     * 返回文件的名称
     *
     * @param file
     * @return
     */
    public static String getFileName(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        if (!TextUtil.isStringAvailable(originalName)) {
            return null;
        }
        // 获取后缀
        String dot = originalName.substring(originalName.lastIndexOf("."));
        // 文件名
        String result = System.currentTimeMillis() + dot;
        return result;
    }


    /**
     * 返回带后缀的文件名称
     *
     * @return
     */
    public static String getFileNameWithDot(String fileType) {
        return getFileNameCode() + "." + fileType;
    }

    /**
     * 返回上传文件的编码之后的文件名称
     *
     * @param file
     * @return
     */
    public static String getFileNameWithDotByMultFile(MultipartFile file) {
        if (file == null) {
            return null;
        }
        return getFileNameCode() + "." + getFileDot(file);
    }

    /**
     * 返回一个新的pdf的文件
     *
     * @return
     */
    public static File getNewPdfFile() {
        // 文件名
        return getRegularFormatFile("pdf");
    }

    /**
     * 返回普通文件
     * 指定后缀
     *
     * @param format
     * @return
     */
    public static File getRegularFormatFile(String format) {
        logger.info("生成指定类型的文件路径:" + format);
        // 文件名
        String fileName = getFileNameCode() + "." + format;
        // 文件日期目录
        String dataPath = ReviewDateFormatUtl.getTodayDateForFilePath();
        // 文件的完整目录 项目路径/普通文件的路径/日期路径/类型路径
        String fullPath = Config.PROJECT_DIR + "/" + Config.REGULAR_FILE_DIR_NAME + "/" + dataPath + "/" + format;
        File dirs = new File(fullPath);
        if (!dirs.exists()) {
            dirs.mkdirs();
        }
        String filePath = fullPath + "/" + fileName;
        logger.info("生成结果为:" + filePath);
        File result = new File(filePath);
        return result;
    }

    /**
     * 返回文件的后缀名
     *
     * @param file
     * @return
     */
    public static String getFileDot(MultipartFile file) {
        if (file == null) {
            return null;
        }
        String fileName = file.getOriginalFilename();
        if (TextUtil.isStringError(fileName)) {
            return null;
        }
        String dot = fileName.substring(fileName.lastIndexOf(".") + 1);
        return dot;
    }

    /**
     * 返回pdf的文件名称
     *
     * @return
     */
    public static String getFileNamePDF() {
        return getFileNameWithDot("pdf");
    }

    /**
     * 返回文件名的编码结果
     *
     * @return
     */
    private static String getFileNameCode() {
        // 时间格式
        String time = ReviewDateFormatUtl.formatDate(new Timestamp(System.currentTimeMillis()), "yyyy_MM_dd_HH_mm_ss");
        // 随机字符串
        return time + "_" + RandomUtil.randomFileNameAppend();
    }

    /**
     * 根据文件名,返回完整路径的文件
     *
     * @param fileName
     * @return
     */
    private static File getFileByFileName(String fileName) {
        // 文件夹名称
        String date = ReviewDateFormatUtl.getTodayDateForFilePath();
        File root = new File(Config.REGULAR_FILE_DIR_NAME + "/" + date);
        if (!root.exists()) {
            root.mkdirs();
        }
        // 返回包含路径,文件名的File实体
        return new File(root + "/" + fileName);
    }

    /**
     * 返回要保存的文件,只设置路径和文件名
     *
     * @param dot 传入要生成的文件的后缀
     * @return
     */
    public static File getFile(String dot) {
        // 文件名
        String result = System.currentTimeMillis() + dot;
        return getFileByFileName(result);
    }


    /**
     * 返回filebean的真实路径
     *
     * @param file
     * @return
     */
    public static String getAbsolutePathForFileBean(FileBean file) {
        if (file == null) {
            return null;
        }
        return Config.getFullLocalPath(file.getPath());
    }
}
