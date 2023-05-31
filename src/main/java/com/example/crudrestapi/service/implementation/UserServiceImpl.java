package com.example.crudrestapi.service.implementation;

import com.example.crudrestapi.exception.EmailAlreadyExistsException;
import com.example.crudrestapi.exception.ResourceNotFoundException;
import com.example.crudrestapi.mapper.UserMapper;
import com.example.crudrestapi.model.User;
import com.example.crudrestapi.payload.UserDTO;
import com.example.crudrestapi.repository.UserRepository;
import com.example.crudrestapi.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Igor Suvorov
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
       // User user = UserMapper.convertDTOToEntity(userDTO);

        Optional<User> optionalUser = userRepository.findByEmail(userDTO.getEmail());

        if (optionalUser.isPresent()) {
            throw new EmailAlreadyExistsException("User with this email already exists");
        }

        User user = modelMapper.map(userDTO, User.class);

        User savedUser = userRepository.save(user);

       // UserDTO savedUserDTO = UserMapper.convertEntityToDTO(savedUser);

        UserDTO savedUserDTO = modelMapper.map(savedUser, UserDTO.class);

        return savedUserDTO;
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("user", "id", id)
        );
     //   return UserMapper.convertEntityToDTO(optionalUser.get());
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
//        return users.stream().map(UserMapper::convertEntityToDTO)
//                .collect(Collectors.toList());
        return users.stream().map((user) -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User existingUser = userRepository.findById(userDTO.getId()).orElseThrow(
                () -> new ResourceNotFoundException("user", "id", userDTO.getId())
        );
        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setEmail(userDTO.getEmail());
        return modelMapper.map(userRepository.save(existingUser), UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("user", "id", id)
        );
        userRepository.deleteById(id);
    }

}
