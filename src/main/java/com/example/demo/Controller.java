package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/producer")
public class Controller {

    @Autowired
    Channels channel;

    @GetMapping("/all/sendto")
    public String sendToAdapter()
    {
        String inputString = "DATA6577702420200921004450T096D00000000027862          Y                     V03                                                                      D1BPU29   0700  0133101000317000C0537330191996  PTRD XS202009242020092111D0005200921EL00000000027862001CXLI                                TCC    P        I          TS  TU       0           00000000010000000             DC00000888000000000000/0000  000C0000000000T     000000000000T0000/0000TAC053733        007    I      I                 D978    SZAEAVKBMSC    11  1 L01256C            CC               A       20200921 00000    09P1 0 0C                      00000000000000000T          014QB0211161T                  3456              123 5678                                                                                                  202009210158082    23               01            N #@R1BNDTRTA 0400  0700040005000700036002700220050200010100               03450                                    0101 01I015                    0   Y                                2020092120200921        10 N  1623000             200921EL00000000027862000200921EL00000000027862000                                                                                                                  Y   #@G1P3MSD   0500  18383M17500010030003 00000000       00000000000000000000000000000000000000000000000000000000000             000000000000000000*N  0000001                  Nm0000000000000000   YY      J 00000000          0  0000000000000000000     DUS1NN             US18383M1751000000000000000000000000 0000000000000000000000000000000000000000000000000000000000 00000000001US00000000                     0000000000000000         BZ77Y62     NN       0000000000000000000NEQIY    NY    NN     {  N   #@H1BNDTRTA 0700  NET00000000008871927001PPRI00000000008880000001PW/H00000000000000000001N P000000000P                                    BAS                 001 INT00000000000000000001+I ICOM0000000007073001-PGRC0000000000000001NI I I0000000000000000SEC0000000000600001-P IPOS0000000000000001NP ISTX0000000000500001-T IGPF0000000000400001NPGP20000000000000001NPGP30000000000000001NPGP40000000000000001NPGP50000000000000001NP000001000000100000000 000000000000000000000 000000000000000000000 000000000000000000000 000000000000000000000                                      W/I00000000000000000001N P00000000008871927D                                                    N                               #@K1BPU30   0360                                     GOBTUN         00000000000000000000000000000 00000000000000000 00000000000000000 000000000                                                                                                                                                                                    001                                  #@L1ZP3TANA 0270  3100000  0000000003100 E00500500P  123 5            000 0000000000000000N                       0     20171003             MS20171003        1     000 0NN000 000000000                             00000000     F                                          #@N1BKP20PS 0220  05M 001CLAYMORE EXCHANGE TRADED FUND T  1 M 002TRUST GUGGENHEIM DOW JONES    T  2 M 003INDUSTRIAL AVERAGE DIV ETF    T  3   004AUTO LIQUIDATION FRAC         C  1 C 005TO CXL PREVIOUS BUY                #@S1BPU30   0200    00969T          01          0000000001000000000000000008880000001T00000000000000000001+T                                                                                            #@U1SEGMSDD 0100                               001100  N   N  00000                                 #@";
        Message<String> message = org.springframework.messaging.support.MessageBuilder
                .withPayload(inputString)
                .build();
        channel.bpsOutChannel().send(message);
        System.out.println("Message Sent to Topic.");
        return "Published";
    }

    @GetMapping("/testIn")
    @StreamListener(value = "bpsIn")
    public void subscribeTo(Object payload) {
        System.out.println(payload);
        System.out.println("Message Received From Topic.");
    }
    @GetMapping("/all")
    public String findAll()
    {
        return "Sample Producer";
    }

    @GetMapping("/publish/{text}")
    public String publishMessage(@PathVariable String text)
    {
        Message<String> message = MessageBuilder
                .withPayload(text)
                .build();
        channel.outChannel().send(message);
        return "Message Sent Successfully -- "+ message;
    }



    @GetMapping("/publish2/{text}")
    public String publishMessage2(@PathVariable String text)
    {
        Message<String> message = MessageBuilder
                .withPayload(text)
                .build();
        channel.inChannel().send(message);
        return "Message2 Sent Successfully -- "+ message;
    }

/*    @StreamListener(value= "testin")
    public void consumeMsgTestIn(String msg){
        System.out.println("From Message TestIn:--"+msg);
    }

    @StreamListener(value= "testout")
    public void consumeMsgTestOut(String msg){
        System.out.println("From Message TestOut:--"+msg);

    }*/

}
