import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { FuncionarioService } from 'app/entities/funcionario/funcionario.service';
import { IFuncionario, Funcionario } from 'app/shared/model/funcionario.model';
import { Sexo } from 'app/shared/model/enumerations/sexo.model';
import { EstadoCivil } from 'app/shared/model/enumerations/estado-civil.model';
import { Escolaridade } from 'app/shared/model/enumerations/escolaridade.model';
import { CategoriaFuncionario } from 'app/shared/model/enumerations/categoria-funcionario.model';
import { Estado } from 'app/shared/model/enumerations/estado.model';

describe('Service Tests', () => {
  describe('Funcionario Service', () => {
    let injector: TestBed;
    let service: FuncionarioService;
    let httpMock: HttpTestingController;
    let elemDefault: IFuncionario;
    let expectedResult: IFuncionario | IFuncionario[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FuncionarioService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Funcionario(
        0,
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        0,
        Sexo.M,
        EstadoCivil.SOLTEIRO,
        Escolaridade.EFC,
        CategoriaFuncionario.PROFISSIONAL_SAUDE,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        Estado.AC,
        currentDate,
        'AAAAAAA',
        'image/png',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dataNascimento: currentDate.format(DATE_FORMAT),
            dataAdmissao: currentDate.format(DATE_FORMAT),
            dataDesligamento: currentDate.format(DATE_FORMAT),
            dataHoraCadastro: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Funcionario', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dataNascimento: currentDate.format(DATE_FORMAT),
            dataAdmissao: currentDate.format(DATE_FORMAT),
            dataDesligamento: currentDate.format(DATE_FORMAT),
            dataHoraCadastro: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataNascimento: currentDate,
            dataAdmissao: currentDate,
            dataDesligamento: currentDate,
            dataHoraCadastro: currentDate,
          },
          returnedFromService
        );

        service.create(new Funcionario()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Funcionario', () => {
        const returnedFromService = Object.assign(
          {
            nomeCompleto: 'BBBBBB',
            foto: 'BBBBBB',
            dataNascimento: currentDate.format(DATE_FORMAT),
            numeroConselhoProfissional: 'BBBBBB',
            cpf: 'BBBBBB',
            rg: 'BBBBBB',
            cnh: 'BBBBBB',
            telefone1: 'BBBBBB',
            telefone2: 'BBBBBB',
            email: 'BBBBBB',
            dataAdmissao: currentDate.format(DATE_FORMAT),
            dataDesligamento: currentDate.format(DATE_FORMAT),
            salario: 1,
            sexo: 'BBBBBB',
            estadoCivil: 'BBBBBB',
            escolaridade: 'BBBBBB',
            funcao: 'BBBBBB',
            descOutraFuncao: 'BBBBBB',
            logradouroNome: 'BBBBBB',
            logradouroNumero: 'BBBBBB',
            logradouroComplemento: 'BBBBBB',
            bairro: 'BBBBBB',
            proximidadesLogradouro: 'BBBBBB',
            cep: 'BBBBBB',
            cidade: 'BBBBBB',
            estado: 'BBBBBB',
            dataHoraCadastro: currentDate.format(DATE_TIME_FORMAT),
            observacao: 'BBBBBB',
            curriculo: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataNascimento: currentDate,
            dataAdmissao: currentDate,
            dataDesligamento: currentDate,
            dataHoraCadastro: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Funcionario', () => {
        const returnedFromService = Object.assign(
          {
            nomeCompleto: 'BBBBBB',
            foto: 'BBBBBB',
            dataNascimento: currentDate.format(DATE_FORMAT),
            numeroConselhoProfissional: 'BBBBBB',
            cpf: 'BBBBBB',
            rg: 'BBBBBB',
            cnh: 'BBBBBB',
            telefone1: 'BBBBBB',
            telefone2: 'BBBBBB',
            email: 'BBBBBB',
            dataAdmissao: currentDate.format(DATE_FORMAT),
            dataDesligamento: currentDate.format(DATE_FORMAT),
            salario: 1,
            sexo: 'BBBBBB',
            estadoCivil: 'BBBBBB',
            escolaridade: 'BBBBBB',
            funcao: 'BBBBBB',
            descOutraFuncao: 'BBBBBB',
            logradouroNome: 'BBBBBB',
            logradouroNumero: 'BBBBBB',
            logradouroComplemento: 'BBBBBB',
            bairro: 'BBBBBB',
            proximidadesLogradouro: 'BBBBBB',
            cep: 'BBBBBB',
            cidade: 'BBBBBB',
            estado: 'BBBBBB',
            dataHoraCadastro: currentDate.format(DATE_TIME_FORMAT),
            observacao: 'BBBBBB',
            curriculo: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataNascimento: currentDate,
            dataAdmissao: currentDate,
            dataDesligamento: currentDate,
            dataHoraCadastro: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Funcionario', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
