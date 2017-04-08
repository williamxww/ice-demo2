package serialization;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author vv
 * @since 2017/4/8.
 */
public class DataTest {

    /**
     * 参考{@link Data1}分析序列化后的字节码
     * 
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        File file = new File("temp.txt");
        FileOutputStream fos = new FileOutputStream(file);
        Data1 data1 = new Data1();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(data1);
        oos.flush();
        fos.close();

        // System.out.println(toHexString(bos.toByteArray()));
    }

    private String toHexString(byte[] ary) {
        StringBuilder sb = new StringBuilder();
        for (byte a : ary) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            // 如果byte为8个1，则其值为-1，转为int时变为0xffffffff
            // 负数在转换时，高位全补1，此处过滤掉高位
            String sTemp = Integer.toHexString(0xff & a);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }
}
