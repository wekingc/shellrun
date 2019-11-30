package pers.jim.shellrun;

import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequestMapping("/run")
public class RunController {

    @PostMapping
    public ReturnModel run(@RequestBody ArgumentModel argument)
            throws InterruptedException , IOException {
        Process pro = null;
        Runtime runTime = Runtime.getRuntime();

        String[] cmdA;
        if("windows".equalsIgnoreCase((argument.getOs()))) {
            cmdA = new String[] { "cmd.exe", "/c", argument.getCommand() };
        }
        else {
            cmdA = new String[] { argument.getCommand() };
        }

        pro = runTime.exec(cmdA);

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
