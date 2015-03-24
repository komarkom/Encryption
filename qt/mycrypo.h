#ifndef MYCRYPO_H
#define MYCRYPO_H

#include "string"
#include "QString"

using namespace std;

class MyCrypo
{
//private:
//    QString inputText;
//    QString outputText;
//    QString code;

public:
    MyCrypo();
//    MyCrypo(QString val);

    QString encryption(QString text,int type, QString key);
    QString decryption(QString text, int type, QString key);

    QString Caesar(QString text, QString key, int route);

    ~MyCrypo();
};

#endif // MYCRYPO_H
