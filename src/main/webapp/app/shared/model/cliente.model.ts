import { Moment } from 'moment';
import { IPlanoEstrategico } from 'app/shared/model/plano-estrategico.model';
import { TipoCliente } from 'app/shared/model/enumerations/tipo-cliente.model';
import { Sexo } from 'app/shared/model/enumerations/sexo.model';
import { EstadoCivil } from 'app/shared/model/enumerations/estado-civil.model';
import { Escolaridade } from 'app/shared/model/enumerations/escolaridade.model';
import { Convenio } from 'app/shared/model/enumerations/convenio.model';
import { Procedencia } from 'app/shared/model/enumerations/procedencia.model';
import { Estado } from 'app/shared/model/enumerations/estado.model';
import { TipoParentesco } from 'app/shared/model/enumerations/tipo-parentesco.model';

export interface ICliente {
  id?: number;
  tipoCliente?: TipoCliente;
  matricula?: string;
  nomeCompleto?: string;
  fotoContentType?: string;
  foto?: any;
  dataNascimento?: Moment;
  sexo?: Sexo;
  estadoCivil?: EstadoCivil;
  escolaridade?: Escolaridade;
  convenio?: Convenio;
  numCarteirinhaConvenio?: string;
  dataValidadeConvenio?: Moment;
  procedencia?: Procedencia;
  profissao?: string;
  cpf?: string;
  rg?: string;
  telefone1?: string;
  telefone2?: string;
  email?: string;
  logradouroNome?: string;
  logradouroNumero?: string;
  logradouroComplemento?: string;
  bairro?: string;
  cep?: string;
  cidade?: string;
  estado?: Estado;
  parentescoResponsavel?: TipoParentesco;
  parentescoResponsavelFinanceiro?: TipoParentesco;
  dataHoraCadastro?: Moment;
  ativo?: boolean;
  observacao?: string;
  planoEstrategicos?: IPlanoEstrategico[];
  responsavel?: ICliente;
  responsavelFinanceiro?: ICliente;
}

export class Cliente implements ICliente {
  constructor(
    public id?: number,
    public tipoCliente?: TipoCliente,
    public matricula?: string,
    public nomeCompleto?: string,
    public fotoContentType?: string,
    public foto?: any,
    public dataNascimento?: Moment,
    public sexo?: Sexo,
    public estadoCivil?: EstadoCivil,
    public escolaridade?: Escolaridade,
    public convenio?: Convenio,
    public numCarteirinhaConvenio?: string,
    public dataValidadeConvenio?: Moment,
    public procedencia?: Procedencia,
    public profissao?: string,
    public cpf?: string,
    public rg?: string,
    public telefone1?: string,
    public telefone2?: string,
    public email?: string,
    public logradouroNome?: string,
    public logradouroNumero?: string,
    public logradouroComplemento?: string,
    public bairro?: string,
    public cep?: string,
    public cidade?: string,
    public estado?: Estado,
    public parentescoResponsavel?: TipoParentesco,
    public parentescoResponsavelFinanceiro?: TipoParentesco,
    public dataHoraCadastro?: Moment,
    public ativo?: boolean,
    public observacao?: string,
    public planoEstrategicos?: IPlanoEstrategico[],
    public responsavel?: ICliente,
    public responsavelFinanceiro?: ICliente
  ) {
    this.ativo = this.ativo || false;
  }
}
