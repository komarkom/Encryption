#include "mycrypo.h"

MyCrypo::MyCrypo()
{

}

MyCrypo::MyCrypo(string val)
{
    this->inputText = val;
}

void MyCrypo::setInputText(string val)
{
    this->inputText = val;
}

void MyCrypo::setOutputText(string val)
{
    this->outputText = val;
}

string MyCrypo::getInputText()
{
    return this->inputText;
}

string MyCrypo::getOutputText()
{
    return this->outputText;
}

string MyCrypo::encryption(string text)
{
    return text;
}

MyCrypo::~MyCrypo()
{

}

