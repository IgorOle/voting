package ru.igorole.voting.util;

import ru.igorole.voting.model.Dish;
import ru.igorole.voting.model.Menu;
import ru.igorole.voting.to.MenuTo;

import java.util.List;
import java.util.stream.Collectors;

public class MenuUtil {

    public static MenuTo asTO(List<Menu> menus) {
        List<Dish> dishes = menus.stream().map(m -> m.getDish()).collect(Collectors.toList());
        return new MenuTo(dishes);
    }
}
