package junit;

import cn.touchfish.utils.MailUtils;
import org.junit.Test;

import java.util.UUID;

/**
 * @ClassName TestMailUtil
 * @Description Details
 * @Author Josen
 * @Create 2020/8/12 12:18
 */
public class TestMailUtil {
    @Test
    public void testing() {
        String str = UUID.randomUUID().toString();
        MailUtils mailUtils = new MailUtils("josen_lii@126.com", str,"JJJ");
        mailUtils.sendMail();
    }
}
