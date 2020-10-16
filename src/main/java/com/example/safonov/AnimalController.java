package com.example.safonov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class AnimalController {

    @Autowired
    AnimalRepository mAnimalRepository;
    @GetMapping("/")
    public String getString() {
        return "Hello, its CSA project petshop in Docker by Nikita Safonov IKBO-04-17";
    }
    @GetMapping("/petshop")
    public List getAllNotes() {

        return mAnimalRepository.findAll();
    }

    @PostMapping("/petshop")
    public Animal createNote( @RequestBody Animal animal) {
        return mAnimalRepository.save(animal);
    }

    @GetMapping("/petshop/id={id}")
    public Animal getNoteById(@PathVariable(value = "id") Integer id) throws AnimalNotFoundException {
        return mAnimalRepository.findById(id)
                .orElseThrow(() -> new AnimalNotFoundException(id));
    }

    @GetMapping("/petshop/kind={kind}")
    public List<Animal> getByType(@PathVariable(value = "kind") String kind) {
        return mAnimalRepository.findAllByKind(kind);
    }

    @GetMapping("/petshop/name={name}")
    public List<Animal> getByName(@PathVariable(value = "name") String name) {
        return mAnimalRepository.findAllByNameContaining(name);
    }

    @PutMapping("/petshop/id={id}")
    public Animal updateNote(@PathVariable(value = "id") Integer id,
                              @RequestBody Animal animalDetails) throws AnimalNotFoundException {

        Animal animal = mAnimalRepository.findById(id)
                .orElseThrow(() -> new AnimalNotFoundException(id));

        animal.setName(animalDetails.getName());
        animal.setKind(animalDetails.getKind());
        animal.setPrice(animalDetails.getPrice());
        return mAnimalRepository.save(animal);
    }

    @DeleteMapping("/petshop/id={id}")
    public ResponseEntity deleteAnimal(@PathVariable(value = "id") Integer id) throws AnimalNotFoundException {
        Animal animal = mAnimalRepository.findById(id)
                .orElseThrow(() -> new AnimalNotFoundException(id));
        mAnimalRepository.delete(animal);
        return ResponseEntity.ok().build();
    }
}
