#ifndef PRINTER_ICE
#define PRINTER_ICE

/** #include <Ice/BuiltinSequences.ice> **/
#include "demo/Show.ice"

module Demo {

    const int codeSuccess = 0;     /** SUCCESS **/
    const int codeFail = -1;       /** FAIL **/

    struct Request{
        long requestId;
        string body;
    };

    struct Response{
        long responseId;
        string body;
    };

    interface Printer  
    {  
        void printString(string s);  
    };

    interface DemoService{
        void say(string s);
        int calculate(int a, int b);
        Response call(Request request);
    };

};


#endif