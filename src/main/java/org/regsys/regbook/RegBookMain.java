package org.regsys.regbook;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


public class RegBookMain {
    public static void main(String[] args) {
        KieServices ks = KieServices.Factory.get();
        Resource resource = ks.getResources().newClassPathResource("rules/rules.drl");
        KieFileSystem kfs = ks.newKieFileSystem();
        kfs.write(resource);
        KieBuilder kieBuilder = ks.newKieBuilder(kfs).buildAll();
        KieModule kieModule = kieBuilder.getKieModule();
        KieContainer kieContainer = ks.newKieContainer(kieModule.getReleaseId());
        KieSession kSession = kieContainer.newKieSession();

        String answer = AskQuestion.ask("Czy w fabule książki występuje niebezpieczeństwo?");

        UserResponse userResponse = new UserResponse();
        userResponse.setAnswer(answer);

        userResponse.setWantDanger(answer.equalsIgnoreCase("tak"));

        kSession.insert(userResponse);
        kSession.fireAllRules();
        kSession.dispose();
    }
}