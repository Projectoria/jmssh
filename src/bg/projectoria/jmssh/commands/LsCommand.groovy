package bg.projectoria.jmssh.commands

import org.codehaus.groovy.tools.shell.CommandSupport
import org.codehaus.groovy.tools.shell.Shell

class LsCommand extends CommandSupport {

    LsCommand(Shell shell) {
        super(shell, "dir", "ls")
    }

    @Override
    Object execute(List list) {
        shell.io.println("something")
        return null  // FIXME
    }
}
