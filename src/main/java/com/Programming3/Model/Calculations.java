package com.Programming3.Model;

public class Calculations
{
    public String compare(String nameFormOne, float salaryFormOne, String nameFormTwo, float salaryFormTwo)
    {
        String compare = "";

        if(salaryFormOne > salaryFormTwo)
        {
            compare = nameFormOne + " has greater salary then " + nameFormTwo;
        }
        else if (salaryFormOne < salaryFormTwo)
        {
            compare = nameFormTwo + " has greater salary then " + nameFormOne;
        }
        else {
            compare = "Salary are the same";
        }

        return compare;
    }

    public float mean(float one, float two)
    {
        float mean = (one + two) / 2;
        return mean;
    }
}
