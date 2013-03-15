package bg.projectoria.jmssh

import bg.projectoria.jmssh.commands.LsCommand
import org.codehaus.groovy.tools.shell.Groovysh
import org.codehaus.groovy.tools.shell.IO

class JMSSh  {

    private final Groovysh sh;

    JMSSh(IO io) {
        sh = new Groovysh(io);
        initEnv();
    }

    JMSSh() {
        sh = new Groovysh();
        initEnv();
    }

    private void initEnv() {
        sh.register new LsCommand(sh)
    }

    int run(final String[] args) {
        sh.run(args);
    }
}
