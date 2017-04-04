module Demo {  
    interface Printer  
    {  
        void printString(string s);  
    };

    interface DemoService{
        void say(string s);
        int calculate(int a, int b);
    };

};