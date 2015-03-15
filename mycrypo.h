#ifndef MYCRYPO_H
#define MYCRYPO_H

#include "string"

using namespace std;

class MyCrypo
{
private:
    string inputText;
    string outputText;
    string code;

public:
    MyCrypo();
    MyCrypo(string val);

    void setInputText(string val);
    void setOutputText(string val);

    string getInputText();
    string getOutputText();

    string encryption(string text);

    ~MyCrypo();
};

#endif // MYCRYPO_H
