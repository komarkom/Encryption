#ifndef MYCRYPO_H
#define MYCRYPO_H

#include "string"
#include "QString"

using namespace std;

class MyCrypo
{
private:
    QString inputText;
    QString outputText;
    QString code;

public:
    MyCrypo();
    MyCrypo(QString val);

    void setInputText(QString val);
    void setOutputText(QString val);

    QString getInputText();
    QString getOutputText();

    QString encryption(QString text,int type, QString key);
    QString decryption(QString text, int type, QString key);

    ~MyCrypo();
};

#endif // MYCRYPO_H
