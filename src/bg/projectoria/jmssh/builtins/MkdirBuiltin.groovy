package bg.projectoria.jmssh.builtins

import bg.projectoria.jmssh.Environment
import org.codehaus.groovy.tools.shell.Shell

class MkdirBuiltin extends Builtin {

    protected MkdirBuiltin(Shell shell, Environment env) {
        super(shell, env, "mkdir", "mkd")
    }

    @Override
    Object call(Object... args) {
        for (arg in args)
            env.mkdir(arg)
    }
}
