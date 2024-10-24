package toby.spring.hellospring;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DoublBraceInitializationTest {

    @Test
    void DoubleBraceInitialization() {

        A a = new A() {
            {
                setInfo("느림");
                setVersion("5v");
                setMode("slow");
            }
        };
        assertThat(a.getInfo()).isEqualTo("느림");
        assertThat(a.getVersion()).isEqualTo("5v");
        assertThat(a.getMode()).isEqualTo("slow");
    }

    static class A {
        private String info;
        private String version;
        private String mode;

        public A() {
        }

        {
            System.out.println("인스턴스 초기화 블럭이란게.. 있구나..");
        }

        {
            System.out.println("그렇구나..");
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }
    }
}
