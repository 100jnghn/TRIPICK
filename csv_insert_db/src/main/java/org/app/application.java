package org.app;

import org.common.JDBCConnect;
import org.controller.Controller;

public class application {
    public static void main(String[] args) {
        JDBCConnect.connect();
        Controller controller = new Controller();

        boolean rs = controller.importFile("travel.csv", true);
        if (rs == true) {
            JDBCConnect.commit();
        } else {
            JDBCConnect.rollback();
        }

        JDBCConnect.close();
    }
}
