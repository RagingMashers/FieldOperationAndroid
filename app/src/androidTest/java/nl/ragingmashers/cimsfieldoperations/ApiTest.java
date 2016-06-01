package nl.ragingmashers.cimsfieldoperations;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.LargeTest;

import java.util.List;

import nl.ragingmashers.cimsfieldoperations.fiop.ApiManager;
import nl.ragingmashers.cimsfieldoperations.fiop.Media;
import nl.ragingmashers.cimsfieldoperations.fiop.Message;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApiTest extends ApplicationTestCase<Application> {
    public ApiTest() {
        super(Application.class);
    }

    @LargeTest
    public void testApi(){
        if(!ApiManager.getInstance().isLoggedIn()){
            ApiManager.getInstance().login("testUser","testPass");
        }
        List<Message> mes = ApiManager.getInstance().getMessages();
        assertNotNull(mes);
        if(mes.size()>0){
            Message m = mes.get(0);
            assertNotNull(mes);
            assertFalse(m.getMessage().isEmpty());
            List<Media> media = m.getMedia();
            assertNotNull(media);
            if(media.size()>0){
                Media media1 = media.get(0);
                assertNotNull(media1.getUrl());
            }
        }
    }
}
