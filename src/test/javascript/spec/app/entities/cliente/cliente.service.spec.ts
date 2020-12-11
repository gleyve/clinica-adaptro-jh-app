import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ClienteService } from 'app/entities/cliente/cliente.service';
import { ICliente, Cliente } from 'app/shared/model/cliente.model';
import { TipoCliente } from 'app/shared/model/enumerations/tipo-cliente.model';
import { Sexo } from 'app/shared/model/enumerations/sexo.model';
import { EstadoCivil } from 'app/shared/model/enumerations/estado-civil.model';
import { Escolaridade } from 'app/shared/model/enumerations/escolaridade.model';
import { Convenio } from 'app/shared/model/enumerations/convenio.model';
import { Procedencia } from 'app/shared/model/enumerations/procedencia.model';
import { Estado } from 'app/shared/model/enumerations/estado.model';
import { TipoParentesco } from 'app/shared/model/enumerations/tipo-parentesco.model';

describe('Service Tests', () => {
  describe('Cliente Service', () => {
    let injector: TestBed;
    let service: ClienteService;
    let httpMock: HttpTestingController;
    let elemDefault: ICliente;
    let expectedResult: ICliente | ICliente[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ClienteService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Cliente(
        0,
        TipoCliente.PACIENTE,
        'AAAAAAA',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        currentDate,
        Sexo.M,
        EstadoCivil.SOLTEIRO,
        Escolaridade.EFC,
        Convenio.UNIMED_CE,
        'AAAAAAA',
        currentDate,
        Procedencia.ENCAMINHAMENTO_MEDICO,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        Estado.AC,
        TipoParentesco.PAI,
        TipoParentesco.PAI,
        currentDate,
        false,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dataNascimento: currentDate.format(DATE_FORMAT),
            dataValidadeConvenio: currentDate.format(DATE_FORMAT),
            dataHoraCadastro: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Cliente', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dataNascimento: currentDate.format(DATE_FORMAT),
            dataValidadeConvenio: currentDate.format(DATE_FORMAT),
            dataHoraCadastro: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataNascimento: currentDate,
            dataValidadeConvenio: currentDate,
            dataHoraCadastro: currentDate,
          },
          returnedFromService
        );

        service.create(new Cliente()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Cliente', () => {
        const returnedFromService = Object.assign(
          {
            tipoCliente: 'BBBBBB',
            matricula: 'BBBBBB',
            nomeCompleto: 'BBBBBB',
            foto: 'BBBBBB',
            dataNascimento: currentDate.format(DATE_FORMAT),
            sexo: 'BBBBBB',
            estadoCivil: 'BBBBBB',
            escolaridade: 'BBBBBB',
            convenio: 'BBBBBB',
            numCarteirinhaConvenio: 'BBBBBB',
            dataValidadeConvenio: currentDate.format(DATE_FORMAT),
            procedencia: 'BBBBBB',
            profissao: 'BBBBBB',
            cpf: 'BBBBBB',
            rg: 'BBBBBB',
            telefone1: 'BBBBBB',
            telefone2: 'BBBBBB',
            email: 'BBBBBB',
            logradouroNome: 'BBBBBB',
            logradouroNumero: 'BBBBBB',
            logradouroComplemento: 'BBBBBB',
            bairro: 'BBBBBB',
            cep: 'BBBBBB',
            cidade: 'BBBBBB',
            estado: 'BBBBBB',
            parentescoResponsavel: 'BBBBBB',
            parentescoResponsavelFinanceiro: 'BBBBBB',
            dataHoraCadastro: currentDate.format(DATE_TIME_FORMAT),
            ativo: true,
            observacao: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataNascimento: currentDate,
            dataValidadeConvenio: currentDate,
            dataHoraCadastro: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Cliente', () => {
        const returnedFromService = Object.assign(
          {
            tipoCliente: 'BBBBBB',
            matricula: 'BBBBBB',
            nomeCompleto: 'BBBBBB',
            foto: 'BBBBBB',
            dataNascimento: currentDate.format(DATE_FORMAT),
            sexo: 'BBBBBB',
            estadoCivil: 'BBBBBB',
            escolaridade: 'BBBBBB',
            convenio: 'BBBBBB',
            numCarteirinhaConvenio: 'BBBBBB',
            dataValidadeConvenio: currentDate.format(DATE_FORMAT),
            procedencia: 'BBBBBB',
            profissao: 'BBBBBB',
            cpf: 'BBBBBB',
            rg: 'BBBBBB',
            telefone1: 'BBBBBB',
            telefone2: 'BBBBBB',
            email: 'BBBBBB',
            logradouroNome: 'BBBBBB',
            logradouroNumero: 'BBBBBB',
            logradouroComplemento: 'BBBBBB',
            bairro: 'BBBBBB',
            cep: 'BBBBBB',
            cidade: 'BBBBBB',
            estado: 'BBBBBB',
            parentescoResponsavel: 'BBBBBB',
            parentescoResponsavelFinanceiro: 'BBBBBB',
            dataHoraCadastro: currentDate.format(DATE_TIME_FORMAT),
            ativo: true,
            observacao: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataNascimento: currentDate,
            dataValidadeConvenio: currentDate,
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

      it('should delete a Cliente', () => {
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
