package com.donation.backend.demo.message.request;

import com.donation.backend.demo.message.response.MonthIncome;

import java.util.ArrayList;
import java.util.List;

public class IncomeYear {

    List<MonthIncome> monthIncomes;

    public IncomeYear() {
        this.monthIncomes = new ArrayList<>();
    }

    public List<MonthIncome> getMonthIncomes() {
        return monthIncomes;
    }

    public void setMonthIncomes(List<MonthIncome> monthIncomes) {
        this.monthIncomes = monthIncomes;
    }

    public void addMonthIncome(MonthIncome monthIncome) {
        this.monthIncomes.add(monthIncome);
    }
}
