package bg.projectoria.jmssh.commands

import bg.projectoria.jmssh.Environment
import org.codehaus.groovy.tools.shell.CommandSupport
import org.codehaus.groovy.tools.shell.Shell

class LsCommand extends CommandSupport {

    private final Environment env

    LsCommand(Shell shell, Environment env) {
        super(shell, "dir", "ls")
        getBinding().setVariable("ls", this)
        this.env = env
    }

    List<MapEntry> call(Object... args) {
        if (args.length == 0)
            return [new MapEntry(env.getcwd(), env.resolve(env.getcwd()))]

        return args.collect() {
            name -> new MapEntry(name, env.resolve(name))
        }
    }

    @Override
    Object execute(List list) {
        shell.io.println(toString(call(list.toArray())))
    }

    String toString(List<MapEntry> entries) {
        entries.collect() {
            e -> "${e.getKey()}:\n${String.valueOf(e.getValue())}\n"
        }.join("\n")
    }

}
