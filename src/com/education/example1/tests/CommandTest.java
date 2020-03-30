package com.education.example1.tests;

import com.education.example1.Solution;
import org.junit.Test;

// TODO тесты пока не проверяют на assert, т.к. всё равно переделывать буду
public class CommandTest {

    @Test
    public void commandAddTest() {
        //-c Сидоров м 24/03/1999
        String[] args = {"-c", "Сидоров", "м", "24/03/1999"};
        Solution.main(args);
    }

    @Test
    public void commandUpdateTest() {
        //-u 2 Сидоров м 24/03/1999
        String[] args = {"-u", "2", "Сидоров", "м", "24/03/1999"};
        Solution.main(args);
    }

    @Test
    public void commandRemoveTest() {
        //-d 2
        String[] args = {"-d", "2"};
        Solution.main(args);
    }

    @Test
    public void commandInfoTest() {
        //-i 2
        String[] args = {"-i", "2"};
        Solution.main(args);
    }
}
