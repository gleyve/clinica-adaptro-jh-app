import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';
import { ICliente } from 'app/shared/model/cliente.model';

export interface IPlanoEstrategico {
  id?: number;
  dataInicio?: Moment;
  dataFim?: Moment;
  detalhamento?: string;
  dataHoraCadastro?: Moment;
  user?: IUser;
  cliente?: ICliente;
}

export class PlanoEstrategico implements IPlanoEstrategico {
  constructor(
    public id?: number,
    public dataInicio?: Moment,
    public dataFim?: Moment,
    public detalhamento?: string,
    public dataHoraCadastro?: Moment,
    public user?: IUser,
    public cliente?: ICliente
  ) {}
}
