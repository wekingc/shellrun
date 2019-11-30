package pers.jim.shellrun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequestMapping("/run")
public class RunController {

//    @Value("${command.os}")
//    public String os;
//
//    @Value("${command.line}")
//    public String line;
    @Autowired
    private CommandProperties command;

    @PostMapping
    public ReturnModel run()
            throws InterruptedException , IOException {

        ArgumentModel argument = new ArgumentModel(command.getOs(), command.getLine());
        Runtime runTime = Runtime.getRuntime();

        String[] cmdA;
        if("windows".equalsIgnoreCase((argument.getOs()))) {
            cmdA = new String[] { "cmd.exe", "/c", argument.getCommand() };
        }
        else {
            cmdA = new String[] { argument.getCommand() };
        }

        Process pro = runTime.exec(cmdA);

        BufferedReader input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(pro.getOutputStream()));

        String line;
        StringBuffer sb = new StringBuffer();
        while ((line = input.readLine()) != null) {
            sb.append(line + "\n");
        }
        input.close();
        output.close();

        pro.destroy();

        return new ReturnModel(true,sb.toString());
    }
}
