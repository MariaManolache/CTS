package ro.ase.acs;

import ro.ase.acs.contracts.Operation;

public class Main {

    public static void main(String[] args) {
        try {

            Operation create = (Operation) Class.forName("ro.ase.acs.Create").getConstructor().newInstance();
            Operation insertData = (Operation) Class.forName("ro.ase.acs.Insert").getConstructor().newInstance();
            Operation readData = (Operation) Class.forName("ro.ase.acs.Read").getConstructor().newInstance();
            Operation close = (Operation) Class.forName("ro.ase.acs.Close").getConstructor().newInstance();

            Orchestrator orchestrator = new Orchestrator(create);
            orchestrator.execute(create);

            orchestrator = new Orchestrator(insertData);
            orchestrator.execute(insertData);

            orchestrator = new Orchestrator(readData);
            orchestrator.execute(readData);

            orchestrator = new Orchestrator(close);
            orchestrator.execute(close);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
