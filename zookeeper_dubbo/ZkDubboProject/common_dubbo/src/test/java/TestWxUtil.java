import com.josen.entity.AccessToken;
import com.josen.utils.WxUtil;
import org.junit.Test;

/**
 * @ClassName TestWxUtil
 * @Description Details
 * @Author Josen
 * @Create 2020/9/5 21:26
 */
public class TestWxUtil {
    @Test
    public void testingGetAccessToken(){
        AccessToken accessToken = WxUtil.getAccessToken();
        System.out.println(accessToken);
    }
    @Test
    public void testCode(){
        String code = WxUtil.getCode();
        System.out.println(code);
    }
    @Test
    public void testScanUrl(){
        String scanUrl = WxUtil.getScanUrl();
        System.out.println(scanUrl);
    }
}
