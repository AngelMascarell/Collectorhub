package com.collectorhub.collectorhub.services.impl;

import com.collectorhub.collectorhub.controller.request.UserFilterRequest;
import com.collectorhub.collectorhub.database.entities.MangaEntity;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.database.repositories.MangaRepository;
import com.collectorhub.collectorhub.database.repositories.UserRepository;
import com.collectorhub.collectorhub.dto.MangaDto;
import com.collectorhub.collectorhub.dto.UserDto;
import com.collectorhub.collectorhub.dto.mappers.AbstractMangaDtoMapper;
import com.collectorhub.collectorhub.dto.mappers.AbstractUserDtoMapper;
import com.collectorhub.collectorhub.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MangaRepository mangaRepository;
    @Autowired
    private AbstractUserDtoMapper userDtoMapper;

    @Autowired
    private AbstractMangaDtoMapper mangaDtoMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDto findByUsername(String username) {
        return userDtoMapper.fromUserEntityToUserDto(userRepository.findByUsername(username));
    }

    @Override
    public UserDto findById(long id) {
        return userDtoMapper.fromUserEntityToUserDto(userRepository.findById(id));
    }

    @Override
    public UserDto findByEmail(String email) {
        return userDtoMapper.fromUserEntityToUserDto(userRepository.findByEmail(email));
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso.");
        }

        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new IllegalArgumentException("El correo electrónico ya está en uso.");
        }

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);

        UserEntity userEntity = userDtoMapper.fromUserDtoToUserEntity(userDto);

        UserEntity savedUserEntity = userRepository.save(userEntity);

        return userDtoMapper.fromUserEntityToUserDto(savedUserEntity);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();

        return userEntities.stream()
                .map(userDtoMapper::fromUserEntityToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public long countAllUsers() {
        return userRepository.count();
    }

    @Override
    public long countUsersSubscribed() {
        List<UserEntity> totalUsers = userRepository.findAll();
        long usersSubscribed = 0;
        for (UserEntity user : totalUsers) {
            if (user.isPremium()) {
                usersSubscribed += 1;
            }
        }
        return usersSubscribed;
    }

    @Override
    public UserDto updateUser(UserDto userDto, long id) {
        UserEntity existingUser = userRepository.findById(id);

        if (userDto.getUsername() != null) {
            existingUser.setUsername(userDto.getUsername());
        }

        if (userDto.getEmail() != null) {
            existingUser.setEmail(userDto.getEmail());
        }

        if (userDto.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }


        if (userDto.getBirthdate() != null) {
            existingUser.setBirthdate(userDto.getBirthdate());
        }

        // No sobreescribimos otros campos como "premium" o "fecha de registro" ya que no se modifican

        UserEntity updatedUser = userRepository.save(existingUser);

        return userDtoMapper.fromUserEntityToUserDto(updatedUser);
    }

    public List<UserDto> filterUsers(UserFilterRequest filter) {
        if (filter == null) {
            throw new IllegalArgumentException("El filtro no puede ser nulo.");
        }

        List<UserEntity> allUsers = userRepository.findAll();
        List<UserDto> allDtoUsers = userDtoMapper.fromUserEntityListToUserDtoList(allUsers);

        System.out.println("Número de usuarios encontrados: " + allUsers.size());

        List<UserDto> filteredUsers = allDtoUsers.stream()
                .filter(user ->
                        (filter.getUsername() != null && user.getUsername() != null && user.getUsername().contains(filter.getUsername())) ||

                                (filter.getEmail() != null && user.getEmail() != null && user.getEmail().contains(filter.getEmail())) ||

                                (filter.getRegisterDate() != null && user.getRegisterDate() != null && !user.getRegisterDate().isBefore(filter.getRegisterDate())) ||

                                (filter.getBirthdate() != null && user.getBirthdate() != null && !user.getBirthdate().isBefore(filter.getBirthdate())) ||

                                (filter.getRole() != null && user.getRole() != null && user.getRole().getName().equals(filter.getRole()))
                )
                .collect(Collectors.toList());

        return filteredUsers;
    }

    @Transactional
    @Override
    public ResponseEntity<String> addMangaToUser(UserEntity user, Long mangaId) {
        Hibernate.initialize(user.getMangas());
        Optional<MangaEntity> mangaOpt = Optional.ofNullable(mangaRepository.findById(mangaId));

        if (mangaOpt.isPresent()) {
            MangaEntity manga = mangaOpt.get();

            boolean alreadyHasManga = user.getMangas().stream()
                    .anyMatch(m -> m.getId().equals(manga.getId()));

            if (alreadyHasManga) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El usuario ya tiene este manga en su colección.");
            }

            user.getMangas().add(manga);
            userRepository.save(user);
            return ResponseEntity.ok("Manga añadido a la colección del usuario.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El manga especificado no fue encontrado.");
        }
    }

    @Override
    public List<MangaDto> getUserMangas(Long userId) {
        UserEntity user = userRepository.findById(userId);
        return mangaDtoMapper.fromMangaEntityListToMangaDtoList(user.getMangas());
    }

    @Transactional
    @Override
    public ResponseEntity<String> addDesiredMangaToUser(UserEntity user, Long mangaId) {
        Hibernate.initialize(user.getDesiredMangas());
        Optional<MangaEntity> mangaOpt = Optional.ofNullable(mangaRepository.findById(mangaId));

        if (mangaOpt.isPresent()) {
            MangaEntity manga = mangaOpt.get();

            boolean alreadyHasManga = user.getDesiredMangas().stream()
                    .anyMatch(m -> m.getId().equals(manga.getId()))
                    || user.getMangas().stream()
                    .anyMatch(m -> m.getId().equals(manga.getId()));


            if (alreadyHasManga) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El usuario ya tiene este manga en su colección o en la lista de deseados.");
            }

            user.getDesiredMangas().add(manga);
            userRepository.save(user);
            return ResponseEntity.ok("Manga añadido a la lista de deseados.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El manga especificado no fue encontrado.");
        }
    }

    @Override
    public List<MangaDto> getUserDesiredMangas(Long userId) {
        UserEntity user = userRepository.findById(userId);
        return mangaDtoMapper.fromMangaEntityListToMangaDtoList(user.getDesiredMangas());
    }


}
