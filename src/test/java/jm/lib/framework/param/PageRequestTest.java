package jm.lib.framework.param;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PageRequestTest {

    @Test
    public void testGetEndOffset() throws Exception {
        PageRequest pr = new PageRequest(1, 20);
        System.out.println("offset=" + pr.getOffset());
        System.out.println("endOffset=" + pr.getEndOffset());
//        Assert.assertEquals();

    }
}