package com.studio314.autowaremanagesys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    public enum OrderState {
        IN_WAITING(1), IN_PROCESSING(2), IN_FINISHED(3),
        OUT_WAITING(4), OUT_PROCESSING(5), OUT_FINISHED(6);

        OrderState(int i) {
            this.stateNum = i;
        }
        private final int stateNum;
        public int getStateNum() {
            return stateNum;
        }
    }

    private int oID;
    private String number;
    private int type;
    private int state;
    private LocalDateTime cTime;
    private LocalDateTime eTime;
    private int cargoID;
    private int num;
    private int wareID;
}
