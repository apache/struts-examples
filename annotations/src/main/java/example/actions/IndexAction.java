package example.actions;

import example.model.Participant;
import org.apache.struts2.action.Action;
import org.apache.struts2.interceptor.parameter.StrutsParameter;

import java.util.ArrayList;
import java.util.List;

public class IndexAction implements Action {

    List<Participant> participantAccessList = new ArrayList<>();

    @Override
    public String execute() throws Exception {
        for (int i = 0; i < 1000; i++) {
            participantAccessList.add(new Participant(i));
        }
        return SUCCESS;
    }

    @StrutsParameter(depth = 2)
    public List<Participant> getParticipantAccessList() {
        return participantAccessList;
    }
}
