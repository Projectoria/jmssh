package bg.projectoria.jmssh

import org.codehaus.groovy.tools.shell.Groovysh
import org.codehaus.groovy.tools.shell.IO

class JMSSh  {

    private final Groovysh sh;

    JMSSh(IO io) {
        sh = new Groovysh(io);
    }

    JMSSh() {
        sh = new Groovysh();
    }

    int run(final String[] args) {
        sh.run(args);
    }
}
