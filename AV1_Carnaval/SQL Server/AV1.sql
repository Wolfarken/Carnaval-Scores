create database Carnaval_AV1
go
use Carnaval_AV1


select * from Escola
select * from Quesito
select * from Jurado
select * from Nota


delete from Nota


declare @saida varchar(max)
exec SP_Nota 2, 0, 0, 7, @saida output
print @saida

declare @saida varchar(max)
exec SP_Nota 2, 0, 1, 6, @saida output
print @saida

declare @saida varchar(max)
exec SP_Nota 2, 0, 2, 7, @saida output
print @saida

declare @saida varchar(max)
exec SP_Nota 2, 0, 3, 8, @saida output
print @saida

declare @saida varchar(max)
exec SP_Nota 2, 0, 4, 9, @saida output
print @saida

declare @saida varchar(max)
exec SP_Nota 2, 1, 0, 10, @saida output
print @saida

declare @saida varchar(max)
exec SP_Nota 2, 1, 1, 5, @saida output
print @saida

declare @saida varchar(max)
exec SP_Nota 2, 1, 2, 6, @saida output
print @saida

declare @saida varchar(max)
exec SP_Nota 2, 1, 3, 7, @saida output
print @saida

declare @saida varchar(max)
exec SP_Nota 2, 1, 4, 8, @saida output
print @saida

declare @saida varchar(max)
exec SP_Nota 2, 2, 0, 9, @saida output
print @saida

declare @saida varchar(max)
exec SP_Nota 2, 2, 1, 10, @saida output
print @saida

declare @saida varchar(max)
exec SP_Nota 2, 2, 2, 5, @saida output
print @saida

declare @saida varchar(max)
exec SP_Nota 2, 2, 3, 6, @saida output
print @saida

declare @saida varchar(max)
exec SP_Nota 2, 2, 4, 7, @saida output
print @saida

drop procedure SP_Nota
go
create procedure SP_Nota (@id_escola int, @id_quesito int, @id_jurado int, @nota float,
							@saida varchar(max) OUTPUT)
as
	declare @nota_menor float,
			@nota_maior float,
			@nota_total float,
			@nota_total_quesitos float

	if (@nota >= 5 and @nota <= 10)
	begin
		if (@id_jurado < 5)
		begin
			insert into Nota values
			(@id_escola, @id_quesito, @id_jurado, @nota, 0, 0, 0, 0)
			set @saida = 'Nota inserida com sucesso'

			--No terceiro valor (id_jurado = 2) em diante terá att da @nota_menor, @nota_maior e @nota_total
			if (@id_jurado > 1)
			begin
				set @nota_menor = (select min(nota)
									from Nota
									where id_escola = @id_escola and id_quesito = @id_quesito)

				set @nota_maior = (select max(nota)
									from Nota
									where id_escola = @id_escola and id_quesito = @id_quesito)

				set @nota_total = (select sum(nota)
									from Nota
									where id_escola = @id_escola and id_quesito = @id_quesito)

				set @nota_total = @nota_total - (@nota_menor + @nota_maior)
	
				update Nota
				set nota_menor = @nota_menor
				where id_escola = @id_escola and id_quesito = @id_quesito

				update Nota
				set nota_maior = @nota_maior
				where id_escola = @id_escola and id_quesito = @id_quesito

				update Nota
				set nota_total = @nota_total
				where id_escola = @id_escola and id_quesito = @id_quesito

				if (@id_jurado = 4)
				begin
					set @nota_total_quesitos = (select sum(nota_total)
												from Nota
												where id_escola = @id_escola and id_jurado = 4)
					update Nota
					set nota_total_quesitos = @nota_total_quesitos
					where id_escola = @id_escola
				end
			end
		end
		else
		begin
			raiserror ('Operação Inválida',16,1)
		end
	end
	else
	begin
		raiserror ('A nota deve ser maior ou igual a 5 e menor ou igual a 10',16,1)
	end




exec SP_Nota_Total

drop procedure SP_Nota_Total
go
create procedure SP_Nota_Total
as
	declare @query varchar(max)

	set @query = 'SELECT id_escola, nota_total_quesitos FROM Nota WHERE id_quesito = 0 and id_jurado = 0 order by nota_total_quesitos desc'

	exec (@query)

	

drop table Escola
go
create table Escola
(
id_escola int not null,
nome_escola varchar(100)
primary key(id_escola)
)
insert into Escola values
(0,'Acadêmicos do Tatuapé'),
(1,'Rosas de Ouro'),
(2,'Mancha Verde'),
(3,'Vai-Vai'),
(4,'X-9 Paulistana'),
(5,'Dragões da Real'),
(6,'Águia de Ouro'),
(7,'Nenê de Vila Matilde'),
(8,'Gaviões da Fiel'),
(9,'Mocidade Alegre'),
(10,'Tom Maior'),
(11,'Unidos de Vila Maria'),
(12,'Acadêmicos do Tucuruvi'),
(13,'Império de Casa Verde')


drop table Jurado
go
create table Jurado
(
id_jurado int not null,
nome_jurado varchar(200)
primary key(id_jurado)
)
insert into Jurado values
(0,'Annys Windwhisper'),
(1,'Barog the World Eater'),
(2,'Calleron The Flaming Sky'),
(3,'Dyotelr Fire of the Eternal Forge'),
(4,'Elundryyel Goddess of the Moon')



drop table Quesito
go
create table Quesito
(
id_quesito int not null,
nome_quesito varchar(200)
primary key (id_quesito)
)
insert into Quesito values
(0,'Comissao de Frente'),
(1,'Evolucao'),
(2,'Fantasia'),
(3,'Bateria'),
(4,'Alegoria'),
(5,'Harmonia'),
(6,'Samba_enredo'),
(7,'Mestre Sala e Porta Bandeira'),
(8,'Enredo')



drop table Nota
go
create table Nota
(
id_escola int not null,
id_quesito int not null,
id_jurado int not null,
nota float,
nota_menor float,
nota_maior float,
nota_total float,
nota_total_quesitos float,
primary key (id_escola, id_quesito, id_jurado),
foreign key (id_escola) references Escola(id_escola),
foreign key (id_quesito) references Quesito(id_quesito),
foreign key (id_jurado) references Jurado(id_jurado)
)
