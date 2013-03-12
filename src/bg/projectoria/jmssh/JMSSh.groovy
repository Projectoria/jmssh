package bg.projectoria.jmssh

import org.codehaus.groovy.tools.shell.Groovysh
import org.codehaus.groovy.tools.shell.IO

class JMSSh extends Groovysh {

    JMSSh(IO io) {
        super(io);
    }

    JMSSh() {
        super();
    }
}
