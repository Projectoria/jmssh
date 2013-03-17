package bg.projectoria.jmssh

import bg.projectoria.jmssh.builtins.Builtin
import org.codehaus.groovy.tools.shell.Groovysh
import org.codehaus.groovy.tools.shell.IO

class JMSSh  {

    private final Groovysh sh;
    private final Environment env;

    JMSSh(IO io) {
        sh = new Groovysh(io);
        env = new Environment();
        init();
    }

    JMSSh() {
        sh = new Groovysh();
        env = new Environment();
        init();
    }

    private void init() {
        Builtin.loadBuiltins(sh, env)
    }

    int run(final String[] args) {
        sh.run(args);
    }
}
