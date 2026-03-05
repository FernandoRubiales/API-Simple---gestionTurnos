package com.example.apiTurnos.services;

import com.example.apiTurnos.dto.DoctorDTORequest;
import com.example.apiTurnos.dto.DoctorDTOResponse;
import com.example.apiTurnos.dto.PacienteDTORequest;
import com.example.apiTurnos.dto.PacienteDTOResponse;
import com.example.apiTurnos.entity.Doctor;
import com.example.apiTurnos.entity.Paciente;
import com.example.apiTurnos.mapper.DoctorMapper;
import com.example.apiTurnos.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    //CREATE doctor
    public DoctorDTOResponse create(DoctorDTORequest doctorRequest){
        if(doctorRepository.existsByMatricula(doctorRequest.getMatricula())){
            throw new RuntimeException("Ya existe un doctor con esta matricula: " + doctorRequest.getMatricula());
        }

        Doctor doctor = doctorMapper.toEntity(doctorRequest);
        Doctor doctorGuardado = doctorRepository.save(doctor);

        return doctorMapper.toResponse(doctorGuardado);
    }

    //GET Doctor por ID
    public DoctorDTOResponse findById(Long id){
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado con ID: " + id));

        return doctorMapper.toResponse(doctor);
    }

    //GET de todos los Doctores
    public List<DoctorDTOResponse> findAll(){
        List<Doctor> doctores = doctorRepository.findAll();
        return doctorMapper.toResponseList(doctores);
    }

    //GET doctores por especialidad
    public List<DoctorDTOResponse> findByEspecialidad(String especialidad){
        List<Doctor> doctores = doctorRepository.findByEspecialidad(especialidad);
        return doctorMapper.toResponseList(doctores);
    }

    //GET doctor por matricula
    public DoctorDTOResponse findByMatricula(String matricula){
        Doctor doctor = doctorRepository.findByMatricula(matricula)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado con matrícula: " + matricula));
        return doctorMapper.toResponse(doctor);
    }

    //UPDATE doctor
    public DoctorDTOResponse update(Long id, DoctorDTORequest doctorRequest){
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado con ID: " + id));

        //Verificar que si cambia la matricula, no este repetida
        String matriculaIngresada = doctorRequest.getMatricula();
        if(!matriculaIngresada.equals(doctor.getMatricula())){
            if(doctorRepository.existsByMatricula(matriculaIngresada)){
                throw new RuntimeException("Ya existe un doctor con matrícula: " + matriculaIngresada);
            }
        }
        doctorMapper.updateEntitytoRequest(doctor, doctorRequest);
        Doctor updateDoctor  = doctorRepository.save(doctor);
        return doctorMapper.toResponse(updateDoctor);

    }

    //DELETE doctor
    public void delete(Long id){
        if (!doctorRepository.existsById(id)) {
            throw new RuntimeException("Doctor no encontrado con ID: " + id);
        }

        doctorRepository.deleteById(id);
    }
}