package com.workintech.fswebs17d1.controller;


import com.workintech.fswebs17d1.entity.Animal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {
    @Value("${course.name}")
    private String courseName;

    @Value("${project.developer.fullname}")
    private String fullName;

    private Map<Integer, Animal> animals =new HashMap<>();

    @GetMapping
    public List<Animal> findAll(){
    return animals.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Animal findAnimal(@PathVariable int id){
        Animal animal = animals.get(id);
        if (animal == null) {
            throw new IllegalArgumentException("Animal with ID " + id + " not found");
        }
        return animal;

    }

    @PostMapping
    public void findAnimal(@RequestParam int id,@RequestParam String name){

            animals.put(id,new Animal(id,name));


    }


    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable int id, @RequestBody Animal animal){
        if(animals.containsKey(id)){
            Animal animal1= animals.get(id);
            animals.put(animal.getId(),animal1);
        }
        return animal;
    }

    @DeleteMapping("/{id}")
    public Animal remove(@PathVariable int id){
        Animal animal= animals.get(id);
        animals.remove(id,animal);
        return animal;
    }

    @GetMapping("/info")
    public String index(){
        return "Kurs adı: "+ courseName+"<br>"+
                "Geliştirici adı : "+fullName;
    }


}
