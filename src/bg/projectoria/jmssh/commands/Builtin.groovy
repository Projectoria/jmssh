package bg.projectoria.jmssh.commands

import bg.projectoria.jmssh.Environment
import org.codehaus.groovy.tools.shell.CommandSupport
import org.codehaus.groovy.tools.shell.Shell

abstract class Builtin extends CommandSupport {

    protected final Environment env

    protected Builtin(Shell shell, Environment env, String name, String shortcut) {
        super(shell, name, shortcut)
        shell.register(this)
        getBinding().setVariable(name, this)
        this.env = env
    }

    abstract Object call(Object... args)

    @Override
    Object execute(List list) {
        shell.io.println toString(call(list.toArray()))
    }

    private static String toString(Object obj) {
        if (isSequence(obj))
            obj.collect() { e -> toString(e) }.join("\n")
        else
            String.valueOf(obj)
    }

    private static boolean isSequence(Object obj) {
        [Collection, Object[]].any { it.isAssignableFrom(obj.getClass()) }
    }

    static List<Builtin> loadBuiltins(Shell shell, Environment env) {
        def result = []
        result.add(new LsBuiltin(shell, env))

        result
    }

}

