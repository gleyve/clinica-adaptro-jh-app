import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';
import { IEspecialidadeSaude } from 'app/shared/model/especialidade-saude.model';
import { Sexo } from 'app/shared/model/enumerations/sexo.model';
import { EstadoCivil } from 'app/shared/model/enumerations/estado-civil.model';
import { Escolaridade } from 'app/shared/model/enumerations/escolaridade.model';
import { CategoriaFuncionario } from 'app/shared/model/enumerations/categoria-funcionario.model';
import { Estado } from 'app/shared/model/enumerations/estado.model';

export interface IFuncionario {
  id?: number;
  nomeCompleto?: string;
  fotoContentType?: string;
  foto?: any;
  dataNascimento?: Moment;
  numeroConselhoProfissional?: string;
  cpf?: string;
  rg?: string;
  cnh?: string;
  telefone1?: string;
  telefone2?: string;
  email?: string;
  dataAdmissao?: Moment;
  dataDesligamento?: Moment;
  salario?: number;
  sexo?: Sexo;
  estadoCivil?: EstadoCivil;
  escolaridade?: Escolaridade;
  funcao?: CategoriaFuncionario;
  descOutraFuncao?: string;
  logradouroNome?: string;
  logradouroNumero?: string;
  logradouroComplemento?: string;
  bairro?: string;
  proximidadesLogradouro?: string;
  cep?: string;
  cidade?: string;
  estado?: Estado;
  dataHoraCadastro?: Moment;
  observacao?: string;
  curriculoContentType?: string;
  curriculo?: any;
  user?: IUser;
  especialidadeSaude?: IEspecialidadeSaude;
}

export class Funcionario implements IFuncionario {
  constructor(
    public id?: number,
    public nomeCompleto?: string,
    public fotoContentType?: string,
    public foto?: any,
    public dataNascimento?: Moment,
    public numeroConselhoProfissional?: string,
    public cpf?: string,
    public rg?: string,
    public cnh?: string,
    public telefone1?: string,
    public telefone2?: string,
    public email?: string,
    public dataAdmissao?: Moment,
    public dataDesligamento?: Moment,
    public salario?: number,
    public sexo?: Sexo,
    public estadoCivil?: EstadoCivil,
    public escolaridade?: Escolaridade,
    public funcao?: CategoriaFuncionario,
    public descOutraFuncao?: string,
    public logradouroNome?: string,
    public logradouroNumero?: string,
    public logradouroComplemento?: string,
    public bairro?: string,
    public proximidadesLogradouro?: string,
    public cep?: string,
    public cidade?: string,
    public estado?: Estado,
    public dataHoraCadastro?: Moment,
    public observacao?: string,
    public curriculoContentType?: string,
    public curriculo?: any,
    public user?: IUser,
    public especialidadeSaude?: IEspecialidadeSaude
  ) {}
}
